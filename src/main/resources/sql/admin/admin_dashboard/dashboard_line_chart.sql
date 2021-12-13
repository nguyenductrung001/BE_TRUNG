with date_range as (
    select
        case
            when :p_date_group_type='D' or :p_date_group_type='default' then to_date(:p_from_date, 'yyyymmdd')
            when :p_date_group_type='W' then trunc(to_date(:p_from_date, 'yyyymmdd'),'iw')
            when :p_date_group_type='M' then trunc(to_date(:p_from_date, 'yyyymmdd'),'mm')
            when :p_date_group_type='Q' then trunc(to_date(:p_from_date, 'yyyymmdd'),'Q')
            when :p_date_group_type='Y' then trunc(to_date(:p_from_date, 'yyyymmdd'),'Y')
            else to_date(:p_from_date, 'yyyymmdd')
            end start_date,
        case
            when :p_date_group_type='D' or :p_date_group_type='default' then to_date(:p_to_date, 'yyyymmdd')
            when :p_date_group_type='W' then trunc(to_date(:p_to_date, 'yyyymmdd'),'iw') + 7 - 1/86400
            when :p_date_group_type='M' then last_day (trunc(to_date(:p_to_date, 'yyyymmdd'), 'mm')) + 1 - 1/86400
            when :p_date_group_type='Q' then trunc(trunc(to_date(:p_to_date, 'yyyymmdd'), 'Q')+93,'mm')- 1/86400
            when :p_date_group_type='Y' then trunc(trunc(to_date(:p_to_date, 'yyyymmdd'),'Y')+370, 'Y')- 1/86400
            else to_date(:p_to_date, 'yyyymmdd')
            end end_date,
        case
            when :p_date_group_type='D' or :p_date_group_type='default' then 1
            when :p_date_group_type='W' then 7
            when :p_date_group_type='M' then 30
            when :p_date_group_type='Q' then 90
            when :p_date_group_type='Y' then 365
            else 1
            end day_step
    from dual
), date_control_temp as (
    select
        case
            when :p_date_group_type in ('D', 'W', 'default') then start_date + (level - 1) * day_step
            when :p_date_group_type='M' then add_months(start_date, level-1)
            when :p_date_group_type='Q' then add_months(start_date, (level-1)*3)
            when :p_date_group_type='Y' then add_months(start_date, (level-1)*12)
            end range_start_date,
        case
            when :p_date_group_type in ('D', 'W', 'default') then start_date + (level) * day_step - 1
            when :p_date_group_type='M' then last_day(add_months(start_date, level-1))
            when :p_date_group_type='Q' then add_months(start_date, (level)*3)- 1/86400
            when :p_date_group_type='Y' then add_months(start_date, (level)*12)- 1/86400
            end range_end_date,
        case
            when :p_date_group_type in ('D', 'W', 'default') then end_date - day_step + 1
            when :p_date_group_type='M' then add_months(end_date, -1)+1
            when :p_date_group_type='Q' then add_months(end_date, -3)+1
            when :p_date_group_type='Y' then add_months(end_date, -12)+1
            end last_range_start_date,
        start_date,
        end_date
    from date_range
connect by
    case
    when :p_date_group_type in ('D', 'W', 'default') then start_date + (level) * day_step - 1
    when :p_date_group_type='M' then last_day(add_months(start_date, level-1))
    when :p_date_group_type='Q' then add_months(start_date, (level)*3)- 1/86400
    when :p_date_group_type='Y' then add_months(start_date, (level)*12)- 1/86400
end <= end_date
), date_control as (
    select range_start_date, range_end_date, last_range_start_date, start_date, end_date,
    case
      when :p_date_group_type='D' or :p_date_group_type='default' then to_char(range_start_date, 'dd/mm/yyyy')
      when :p_date_group_type='W' then 'W'||to_char(range_start_date, 'iw')||'/'||to_char(range_start_date,'yy')||'<br/><small>('||to_char(range_start_date,'dd/mm/yyyy')||'=>'||to_char(range_end_date,'dd/mm/yyyy')||')</small>'
      when :p_date_group_type='M' then to_char(range_start_date, 'mm/yyyy')
      when :p_date_group_type='Q' then 'Q'||to_char(range_start_date,'q/yy')||'<br/><small>('||to_char(range_start_date,'dd/mm/yyyy')||'=>'||to_char(range_end_date,'dd/mm/yyyy')||')</small>'
      when :p_date_group_type='Y' then to_char(range_start_date, 'yyyy')
      else to_char(range_start_date,'dd/mm/yyyy')
  end displaydate,
  trunc(range_end_date) - trunc(range_start_date) + 1 as numberofday
    from date_control_temp
    order by range_start_date
), applicant as (
    select j.qty_person, j.start_recruitment_date, jr.*, ps.name job_register_status
    from jobs j
    left join job_register jr on jr.job_id = j.id
    left join profile_status ps on ps.id = jr.profile_status_id
), temp_result as (
    select date_control.range_start_date, date_control.displaydate, nvl(qty_person, 0) qty_person, count(id) success_recruitment
    from date_control
        left join applicant on start_recruitment_date between start_date and end_date
    group by date_control.range_start_date, date_control.displaydate, qty_person
)
select LISTAGG(displaydate, ',') WITHIN GROUP (ORDER BY range_start_date) label,
    LISTAGG(qty_person, ',') WITHIN GROUP (ORDER BY range_start_date) qty_person,
    LISTAGG(success_recruitment, ',') WITHIN GROUP (ORDER BY range_start_date) success_recruitment
from temp_result
