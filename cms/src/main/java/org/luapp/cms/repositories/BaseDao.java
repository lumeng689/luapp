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
public interface BaseDao<K, T> {

    T get(K id);

    List<T> search(Map<String, Object> parameters);

    void save(T entity);

    void delete(K id);
}
