package org.luapp.cms.repositories;

import java.util.List;
import java.util.Map;

/**
 * Dao的基类，封装一些公共的代码
 * 
 * @author nq
 *
 * @param <T>
 */
public interface BaseDao<T> {

    T get(Long id);

    List<T> search(Map<String, Object> parameters);

    void save(T entity);

    void delete(Long id);
}
