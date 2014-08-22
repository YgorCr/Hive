/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ygor
 */

public interface DaoIF<T> {
	
    public abstract T create(HashMap<String, Object> obj) throws IOException, ClassNotFoundException;
    
    public abstract void delete(Long id) throws FileNotFoundException, IOException, ClassNotFoundException;
    
    public abstract void update(Long id, HashMap<String, Object> modifiedObj) throws ClassNotFoundException, IOException;
    
    public abstract T get(Long id) throws ClassNotFoundException, IOException;

    public abstract List<T> listAll() throws ClassNotFoundException, IOException;
    
    public abstract List<T> listAll(Long max, Long offset) throws ClassNotFoundException, IOException;

    public abstract List<T> findBy(HashMap<String, Object> attrQuery) throws ClassNotFoundException, IOException;
    
    public abstract List<T> findBy(HashMap<String, Object> attrQuery, Long max, Long offset) throws ClassNotFoundException, IOException;
    
    public abstract T findOneBy(HashMap<String, Object> attrQuery) throws ClassNotFoundException, IOException;

}
