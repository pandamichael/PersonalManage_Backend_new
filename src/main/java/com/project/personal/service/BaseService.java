package com.project.personal.service;

import com.project.personal.dto.response.ApiPageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseService {

    @Autowired
    private ModelMapper modelMapper;

    protected <D, R> R convertSingleObject(D source, Class<R> targetClass) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, targetClass);
    }

    protected <D, R> List<R> convertObjectList(List<D> sourceList, Class<R> targetClass) {
        if (sourceList.isEmpty()) {
            return new ArrayList<>();
        }

        return sourceList.stream()
                .map(source -> modelMapper.map(source, targetClass))
                .toList();
    }

    protected <T> ApiPageResponse<T> setPageResponse(List<T> resList, int totalCount) {
        ApiPageResponse<T> res = new ApiPageResponse<>();
        res.setData(resList);
        res.setCount(totalCount);

        return res;
    }
}
