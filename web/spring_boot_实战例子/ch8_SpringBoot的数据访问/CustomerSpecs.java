package com.example.demo.specs;

import static com.google.common.collect.Iterables.toArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;


// 当输入的对象(example)有字符串类型的字段值时，会自动对这个字段值进行like查询
// 当有非字符串类型的字段值时，会进行等于查询，
// 对象(example)当有多个值不为空的时候，会自动构造多个查询条件，见注释5
// 对象(example)所有值为空的时候，默认查询出所以记录
public class CustomerSpecs {

	// 1 定义一个返回值为 Specification 的方法byAuto, 这里使用的是泛型T,所以这个Specification是可以用于任意的实体类的，
	// 它接受的参数是entityManager和当前的包含值作为查询条件的实体类对象
	public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) { //1
		// 2 获取当前实体类对象类的类型
		final Class<T> type = (Class<T>) example.getClass();//2

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 3 新建Predicate 列表的存储构造的查询条件
				List<Predicate> predicates = new ArrayList<>(); //3

				// 4 获取实体类的EntityType, 我们可以从EntityType获得实体类的属性
				EntityType<T> entity = entityManager.getMetamodel().entity(type);//4

				// 5 对实体类所有属性做循环
				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {//5
					// 6 获取实体类对象某一属性的值
					Object attrValue = getValue(example, attr); //6
					if (attrValue != null) {

						if (attr.getJavaType() == String.class) { //7 当前属性值为字符类型的时候

							if (!StringUtils.isEmpty(attrValue)) { //8 字符不为空时

								// 构造当前属性like(前后%)属性值查询条件，并添加到条件的列表中
								predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue))); //9
							}
						} else {
							// 其余情况下构造的属性和属性值equal查询条件，并添加到条件列表中。
							predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue)); //10
						}
					}

				}
				//11 将条件列表转换为 Predicate
				return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));//11
			}

			/**
			 * 12 通过反射获取实体类对象对应属性的属性值
			 */
			private <T> Object getValue(T example, Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			
			/**
			 * 13 获取实体类当前属性的SingularAttribute,SingularAttribute包含实体类的某个单独属性
			 */
			private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) { 
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}

		};

	}
	
	/**
	 * 14 构造like查询模式，即前后加%
	 */
	static private String pattern(String str) {
		return "%" + str + "%";
	}

}

// 注意，对象(example)的实体类中定义的数据类型要用包装类型(Long, Integer),不能使用原始类型long.int.
// 因为 Spring MVC中，使用院士数据类型会自动初始化为0,不是空，导致构造条件失败
