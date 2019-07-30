package com.bsq.fresh.converter;


import com.bsq.fresh.dto.AddRessDTo;
import com.bsq.fresh.form.AddRessForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddRessDtoConertter {
    public static AddRessDTo convert(AddRessForm addRessForm){

        AddRessDTo addRessDTo=new AddRessDTo();

        addRessDTo.setByName(addRessForm.getName());
        addRessDTo.setByPhone(addRessForm.getPhone());
        addRessDTo.setByProvince(addRessForm.getByProvince());
        addRessDTo.setByCity(addRessForm.getByCity());
        addRessDTo.setByCityname(addRessForm.getByCityname());
        addRessDTo.setByAddress(addRessForm.getByAddress());
        addRessDTo.setByOpenid(addRessForm.getByOpenid());

        return addRessDTo;
    }
}
