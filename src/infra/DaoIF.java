/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infra;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ygor
 */

public interface DaoIF<T> {
	
    public abstract T create(HashMap<String, Object> obj);
    
    public abstract void delete(Long id);
    
    public abstract void update(Long id, T modifiedObj);
    
    public abstract T get(Long id);

    public abstract List<T> listAll();
    
    public abstract List<T> listAll(Long max, Long offset);

    public abstract List<T> findBy(HashMap<String, Object> attrQuery);
    
    public abstract List<T> findBy(HashMap<String, Object> attrQuery, Long max, Long offset);
    
    public abstract T findOneBy(HashMap<String, Object> attrQuery);

}
