package com.itsol.recruit_managerment.service.impl;

import com.itsol.recruit_managerment.model.Company;
import com.itsol.recruit_managerment.repositories.CompanyRepo;
import com.itsol.recruit_managerment.service.CompanyService;
import com.itsol.recruit_managerment.vm.CompanyVM;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServicempl implements CompanyService {

    private CompanyRepo companyRepo;
    @Override
    public List<Company> getAllCompany() {
        return companyRepo.findAll();
    }


    public int update(CompanyVM companyVM , Long id){
        Company company = companyRepo.getCompanyById(id);
        try {
            // truongbb - tách việc tạo object company ra 1 hàm riêng, dùng builder, không dùng liên tục các hàm set
            company.setId(id);
            company.setName(companyVM.getName());
            company.setEmail(companyVM.getEmail());
            company.setTaxCode(companyVM.getTax_code());
            company.setHotLine(companyVM.getHot_line());
            company.setHeadOffice(companyVM.getHead_office());
            company.setAvatar(companyVM.getAvatar());
            company.setBackdropImg(companyVM.getBackdrop_img());
            company.setDescription(companyVM.getDescription());
            company.setLinkWeb(company.getLinkWeb());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                company.setDateIncoporation(sdf.parse(String.valueOf(companyVM.getDate_incoporation())));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            try {
                company.setTaxDate(sdf1.parse(String.valueOf(companyVM.getTax_date())));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            company.setDelete(companyVM.getIs_delete());
            companyRepo.save(company);

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


}
