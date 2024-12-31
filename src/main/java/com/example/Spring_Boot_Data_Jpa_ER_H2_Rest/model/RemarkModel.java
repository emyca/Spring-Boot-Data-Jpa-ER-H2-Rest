package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Remark;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RemarkModel {
    private Long id;
    private String consideration;

    public static RemarkModel getModel(Remark remark) {
        RemarkModel model = new RemarkModel();
        model.setId(remark.getId());
        model.setConsideration(remark.getConsideration());
        return model;
    }

    public static Long getArticleId(Remark remark) {
       return remark.getArticle().getId();
    }

    public static List<RemarkModel> getModel(List<Remark> remarkList) {
        List<RemarkModel> modelList = new ArrayList<>();
        if (remarkList != null) {
            for (Remark remark : remarkList) {
                RemarkModel model = new RemarkModel();
                model.setId(remark.getId());
                model.setConsideration(remark.getConsideration());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
