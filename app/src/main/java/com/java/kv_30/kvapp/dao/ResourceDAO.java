package com.java.kv_30.kvapp.dao;

import com.java.kv_30.kvapp.dto.Permission;
import com.java.kv_30.kvapp.dto.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TanyaOhotnik on 06.11.2017.
 */

public class ResourceDAO implements InterfaceDAO<Resource> {
    public Resource getResourceById(Long id){
        return getMockList().get((int)(long)id);
    }

    @Override
    public List<Resource> getAll() {
        return getMockList();
    }

    private List<Resource> getMockList() {
        List<Resource> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Resource resource = new Resource();
            resource.setName("file"+i);
            resource.setSize(i*100);
            resource.setExpirationTime(new Date());
            resource.setPermission(Permission.ALL_USERS);
            resource.setId((long)i);
            list.add(resource);
        }
        return list;
    }
}
