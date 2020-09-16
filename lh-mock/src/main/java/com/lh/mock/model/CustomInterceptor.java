package com.lh.mock.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class CustomInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -8368478079693753043L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.err.printf("save entity:" + entity);
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		Set<String> annotatedFieldsSet = getAnnotatedFieldsSet(entity, BPMField.class);
		IntStream.rangeClosed(0, currentState.length - 1)
				.filter(index -> annotatedFieldsSet.contains(propertyNames[index])).forEach(index -> {
					Object prevValue = previousState[index];
					Object currentValue = currentState[index];
					String entityName = entity.getClass().getCanonicalName();
					System.err.println("prevValue: " + prevValue + ", currentValue: " + currentValue + "entityName: "
							+ entityName);
				});
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	private Set<String> getAnnotatedFieldsSet(Object entity, Class<BPMField> class1) {
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		return Arrays.stream(declaredFields)
				.filter(field -> field.getAnnotation(class1) != null)
				.map(Field::getName).collect(Collectors.toSet());
	}
}
