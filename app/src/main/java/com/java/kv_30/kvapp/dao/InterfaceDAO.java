package com.java.kv_30.kvapp.dao;

import java.util.List;

/**
 * Created by TanyaOhotnik on 06.11.2017.
 */

interface InterfaceDAO<T> {
    List<T> getAll();

}
