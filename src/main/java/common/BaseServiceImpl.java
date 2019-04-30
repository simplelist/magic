package common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author kun
 * @DATETIME 2018/7/26 上午9:09
 * 基础service 方法
 */
public abstract class BaseServiceImpl<M extends CustomRepository, T> {

    public static final String NOT_NULL = "查询对象不能为空";
    private static final String ID_FIELD = "id";
    private static Map<Class, List<Field>> classFiledMap = new HashMap<>();
    @Autowired
    protected M baseMapper;
    protected Class<T> modelClass;

    /**
     * 设定对象不存在的提示文案
     * 如: 该方法返回  雇员
     * 则: 最终结果为 雇员不存在
     *
     * @return
     */
    public abstract String noticeText();

    /**
     * bean初始化完场进行处理
     * 将类的参数即:M,T 获取到准确的类型,以便于后续使用
     */
    @PostConstruct
    private void processDependency() {
        Type[] typeArguments = TypeUtil.getTypeArguments(this.getClass().getGenericSuperclass());

        if (ArrayUtils.isEmpty(typeArguments)) {
            return;
        }
        //下面这个地方报空指针，所以加上了上面的方法
        if (typeArguments.length != 2) {
            return;
        }
        modelClass = (Class<T>) TypeUtil.getClass(typeArguments[1]);
    }


    @Transactional(rollbackFor = Exception.class)
    public int insert(T entity) {
        searchObjectNotNull(entity);
        baseMapper.insertSelective(entity);
        return (int) SystemMetaObject.forObject(entity).getValue("id");
    }


    /**
     * 批量插入
     *
     * @param entityList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertBatch(List<T> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return 0;
        }
        return baseMapper.insertList(entityList);
    }


    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return baseMapper.deleteByPrimaryKey(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateById(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 批量更新
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateByIds(List<T> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return 0;
        }
        return (int) entityList.stream().map(s -> baseMapper.updateByPrimaryKeySelective(s)).count();
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateByExample(T entry, Example example) {
        return baseMapper.updateByExampleSelective(entry, example);
    }

    /**
     * 获取对象
     *
     * @param id
     * @param checkNotNull 是否需要检查空对象
     * @return
     */
    public T selectById(int id, boolean checkNotNull) {
        Object obj = baseMapper.selectByPrimaryKey(id);
        resultNotNull(obj, checkNotNull);
        return (T) obj;
    }

    /**
     * 获取对象
     *
     * @param id
     * @return
     */
    public T selectById(int id) {
        return selectById(id, false);
    }

    /**
     * 获取对象
     *
     * @param t
     * @param checkNotNull 是否需要检查空对象
     * @return
     */
    public T selectOne(T t, boolean checkNotNull) {
        searchObjectNotNull(t);
        Object obj = baseMapper.selectOne(t);
        resultNotNull(t, checkNotNull);
        return (T) obj;
    }

    /**
     * 获取对象的数量
     *
     * @param t
     * @return
     */
    public int selectCount(T t) {
        return baseMapper.selectCount(t);
    }

    /**
     * 分页获取数据
     *
     * @param example
     * @return
     */
    public PageInfo<T> selectPage(Example example) {
        return new PageInfo<>(selectByExample(example));
    }

    /**
     * 查询不分页
     *
     * @param example
     * @return
     */
    public List<T> selectByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    public int selectCountByExample(Example example) {
        return baseMapper.selectCountByExample(example);
    }

    /**
     * 根据某个字段 进行in 查询
     *
     * @param properties
     * @param idList
     * @return
     */
    public List<T> selectByProperties(String properties, List idList) {
        if (CollUtil.isEmpty(idList)) {
            return CollUtil.newArrayList();
        }
        Example example = new Example(modelClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(properties, idList);
        return baseMapper.selectByExample(example);
    }

    public Map<Integer, T> selectMapByProperties(String searchProperty, List list, String extractFromListProperty) {
        return selectByProperties(searchProperty, list, extractFromListProperty).stream().collect(Collectors.toMap(t -> Convert.toInt(SystemMetaObject.forObject(t).getValue(searchProperty)), Function.identity(), (k1, k2) -> k2));
    }

    public Map<Integer, T> selectMapByPropertiesGroupByIntField(String searchProperty, int value, String groupingFiled) {
        return selectByProperties(searchProperty, value).stream().collect(Collectors.toMap(t -> Convert.toInt(SystemMetaObject.forObject(t).getValue(groupingFiled)), Function.identity(), (k1, k2) -> k2));
    }

    public Map<String, T> selectMapByPropertiesGroupByStringField(String searchProperty, int value, String groupingFiled) {
        return selectByProperties(searchProperty, value).stream().collect(Collectors.toMap(t -> Convert.toStr(SystemMetaObject.forObject(t).getValue(groupingFiled)), Function.identity(), (k1, k2) -> k2));
    }

    public Map<Integer, List<T>> selectMapListByProperties(String searchProperty, String groupingFiled, List list, String extractFromListProperty) {
        return selectByProperties(searchProperty, list, extractFromListProperty).stream().collect(Collectors.groupingBy(t -> Convert.toInt(SystemMetaObject.forObject(t).getValue(groupingFiled))));
    }

    public Map<Integer, List<T>> selectMapListByProperties(String searchProperty, List list, String extractFromListProperty) {
        return selectByProperties(searchProperty, list, extractFromListProperty).stream().collect(Collectors.groupingBy(t -> Convert.toInt(SystemMetaObject.forObject(t).getValue(searchProperty))));
    }

    public List<T> selectByProperties(String property, int propertyValue) {
        return selectByProperties(property, Convert.toStr(propertyValue));
    }

    public List<T> selectByProperties(String propertyField, String propertyValue) {
        Example example = new Example(modelClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(propertyField, propertyValue);
        return selectByProperties(propertyField, CollUtil.newArrayList(propertyValue));
    }

    public List<T> selectByProperties(String mapKeyProperty, List list, String extractFromListProperty) {
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        List collect = (List) list.stream().map(t -> SystemMetaObject.forObject(t).getValue(extractFromListProperty)).collect(toList());
        if (CollUtil.isEmpty(collect)) {
            return CollUtil.newArrayList();
        }
        return selectByProperties(mapKeyProperty, collect);
    }

    public List<T> selectByProperties(T entity) {
        searchObjectNotNull(entity);
        Example example = new Example(modelClass);
        processCriteria(entity, modelClass, example.createCriteria());
        return selectByExample(example);
    }

    public T selectOneByProperties(T entity, boolean check) {
        searchObjectNotNull(entity);
        Example example = new Example(modelClass);
        processCriteria(entity, modelClass, example.createCriteria());
        List<T> t = baseMapper.selectByExample(example);
        if (CollUtil.isEmpty(t)) {
            if (!check) {
                return null;
            } else {
                resultNotNull(null, check);
            }
        }
        return t.get(0);
    }

    public T selectOneByProperties(T entity) {
        return selectOneByProperties(entity, false);
    }

    protected void processCriteria(T entity, Class<T> modelClass, Example.Criteria criteria) {
        List<Field> list = classFiledMap.get(modelClass);
        if (CollUtil.isEmpty(list)) {
            list = CollUtil.newArrayList(ReflectUtil.getFields(modelClass));
        }
        String filedName;
        Object filedValue;
        for (Field field : list) {
            filedName = field.getName();
            if (filedName.equals("serialVersionUID")) {
                continue;
            }
            filedValue = SystemMetaObject.forObject(entity).getValue(field.getName());
            if (filedValue == null) {
                continue;
            }
            criteria.andEqualTo(filedName, filedValue);
        }
    }

    private void searchObjectNotNull(T t) {
        ValidateUtil.isTrue(t == null, NOT_NULL);
    }

    private void resultNotNull(Object t, boolean check) {
        if (check) {
            ValidateUtil.notFound(t, noticeText());
        }
    }


    public T selectOneByProperties(String propertyField, String propertyValue, boolean checkNull) {
        List<T> list = selectByProperties(propertyField, CollUtil.newArrayList(propertyValue));
        if (checkNull) {
            ValidateUtil.isTrue(CollUtil.isEmpty(list), noticeText());
        } else {
            if (CollUtil.isEmpty(list)) {
                return null;
            }
        }
        return list.get(0);
    }

    public T selectOneByProperties(String propertyField, int propertyValue, boolean checkEmpty) {
        return selectOneByProperties(propertyField, propertyValue + "", checkEmpty);
    }

    /**
     * 根据 id 列表 进行in查询
     *
     * @param idList
     * @return
     */
    public List<T> selectByIds(List<? extends Serializable> idList) {
        return selectByProperties(ID_FIELD, idList);
    }

    public List<T> select(T t) {
        searchObjectNotNull(t);
        return baseMapper.select(t);
    }

    public List<T> selectAll() {
        return baseMapper.selectAll();
    }


}
