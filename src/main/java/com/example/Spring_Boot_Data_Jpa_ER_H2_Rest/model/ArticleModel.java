package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Article;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Remark;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleModel {
    private Long id;
    private String title;
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Remark> remarks;

    public static ArticleModel getModel(Article article) {
        ArticleModel model = new ArticleModel();
        model.setId(article.getId());
        model.setTitle(article.getTitle());
        model.setContent(article.getContent());
        List<Remark> remarkList = new ArrayList<>();
        model.setRemarks(remarkList);
        List<Remark> _remarkList = article.getRemarks();
        if (_remarkList != null) {
            for (Remark remark : _remarkList) {
                Remark _remark = new Remark();
                _remark.setId(remark.getId());
                _remark.setConsideration(remark.getConsideration());
                model.getRemarks().add(_remark);
            }
            model.setRemarks(model.getRemarks());
        }
        return model;
    }
}
