with time_filtered_job as (
    select *
    from jobs
    where start_recruitment_date between to_date(:p_from_date, 'YYYYMMDD') and to_date(:p_to_date, 'YYYYMMDD')
), all_job as (
    select count(j.id) all_job
    from jobs j
             join job_status js on j.status_job_id = js.id
    where js.status_name in ('J_RECRUITING', 'J_STOP', 'J_COLSED')
      and start_recruitment_date > to_date(:p_from_date, 'YYYYMMDD')
), not_done_job_temp as (
    select j.id, j.qty_person, j.due_date, count(jr.id) sucess_job_register
    from time_filtered_job j
             left join job_register jr on jr.job_id = j.id
             left join profile_status ps on ps.id = jr.profile_status_id and ps.name = 'P_SUCCESS'
    group by j.id, j.qty_person, j.due_date
), not_done_job as (
    select count(id) not_done_job
    from not_done_job_temp
    where qty_person > sucess_job_register
      and sysdate - 7 <= due_date
), total_view_job as (
    select sum(views) total_view_job
    from time_filtered_job
), waiting_for_interview as (
    select count(distinct jr.id) waiting_for_interview
    from time_filtered_job j
             left join job_register jr on jr.job_id = j.id
             left join profile_status ps on ps.id = jr.profile_status_id and ps.name = 'P_INTERVIEW'
), interviewing as (
    select count(distinct jr.id) interviewing
    from time_filtered_job j
             left join job_register jr on jr.job_id = j.id
             left join profile_status ps on ps.id = jr.profile_status_id and ps.name = 'P_INTERVIEWING'
), total_apply as (
    select count(*) total_apply
    from time_filtered_job j
             left join job_register jr on jr.job_id = j.id
), sucess_recruited_applicant as (
    select count(jr.id) sucess_recruited_applicant
    from time_filtered_job j
             left join job_register jr on jr.job_id = j.id
             left join profile_status ps on ps.id = jr.profile_status_id and ps.name = 'P_SUCCESS'
)
select all_job,
       not_done_job,
       nvl(total_view_job, 0) total_view_job,
       waiting_for_interview,
       interviewing,
       total_apply,
       sucess_recruited_applicant
from all_job,
     not_done_job,
     total_view_job,
     waiting_for_interview,
     interviewing,
     total_apply,
     sucess_recruited_applicant
