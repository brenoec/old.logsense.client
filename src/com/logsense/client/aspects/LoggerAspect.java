package com.logsense.client.aspects;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import com.google.gson.Gson;
import com.logsense.client.InteractionBuilder;
import com.logsense.client.LogSense;
import com.logsense.client.entities.InteractionEntity;
import com.logsense.client.entities.LocationEntity;
import com.logsense.client.entities.ParameterEntity;
import com.logsense.client.entities.locations.InnerLocationEntity;

public aspect LoggerAspect {

	private static final Gson gson = new Gson();

	// pointcut onException(): handler(Exception) && !execution(* com.logsense.client..*.*(..));

	pointcut onMethodCall(): execution(* *(..)) && !execution(* com.logsense.client..*.*(..));

	//	@SuppressAjWarnings({"adviceDidNotMatch"})
	//	before(): onException(){
	//		System.out.println("AAA ******************");
	//	}
	//
	//	@SuppressAjWarnings({"adviceDidNotMatch"})
	//	after() throwing(Exception ex): onMethodCall() {
	//		InteractionEntity interaction = InteractionBuilder.getInteraction();
	//		interaction.addStatus(ex.getMessage());
	//
	//		System.out.println("******************");
	//
	//		try {
	//			LogSense.log(interaction);
	//		} catch (ClientProtocolException e) {
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}

	@SuppressAjWarnings({"adviceDidNotMatch"})
	before(): onMethodCall() {
		String methodName = thisJoinPoint.getStaticPart().getSignature().getName();
		boolean isEnum = thisJoinPoint.getSourceLocation().getWithinType().isEnum();

		if (!methodName.contains("$") && !isEnum) {

			String fileName = thisJoinPoint.getSourceLocation().getFileName();
			String namespace = thisJoinPoint.getStaticPart().getSignature().getDeclaringType().getPackage().getName();

			InnerLocationEntity location = new InnerLocationEntity();

			location.setSystem(LogSense.getSystem());
			location.setSolution(LogSense.getSolution());
			location.setComponent(LogSense.getComponent());

			location.setSection(namespace);
			location.setElement(fileName);
			location.setRoutine(methodName);

			Object[] values = thisJoinPoint.getArgs();
			String[] parameters = ((CodeSignature) thisJoinPoint.getStaticPart().getSignature()).getParameterNames();

			ArrayList<ParameterEntity> inputs = null;

			if (values.length > 0) {
				inputs = new ArrayList<ParameterEntity>();
			}

			for (int i = 0; i < values.length; i++) {
				ParameterEntity parameter = new ParameterEntity();

				parameter.setName(parameters[i]);
				parameter.setType(values[i].getClass().getSimpleName());
				parameter.setValue(gson.toJson(values[i]));

				inputs.add(parameter);
			}

			location.setInputs(inputs);

			LocationEntity currentLocation = InteractionBuilder.getCurrentLocation();

			if (currentLocation == null) {
				InteractionBuilder.getInteraction().getLocations().add(location);
			} else {
				currentLocation.getLocations().add(location);
				location.setParent(currentLocation);
			}

			InteractionBuilder.stack.push(methodName);
			InteractionBuilder.setCurrentLocation(location);

			//			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			//			String callerMethodName;
			//
			//			if (stack.length > 2) {
			//				callerMethodName = stack[2].getMethodName();
			//			} else if (stack.length > 1) {
			//				callerMethodName = stack[1].getMethodName();
			//			} else {
			//				callerMethodName = stack[0].getMethodName();
			//			}

			//			InnerLocationEntity current = (InnerLocationEntity) InteractionBuilder.getCurrentLocation();
			//			InnerLocationEntity parent = (InnerLocationEntity) current.getParent();
			//
			//			System.out.println(String.format("before: %s ~ %s", (current == null) ? "null" : current.getRoutine(), (parent == null) ? "null" : parent.getRoutine()));
			System.out.println(gson.toJson(InteractionBuilder.stack));
		}
	}

	@SuppressAjWarnings({"adviceDidNotMatch"})
	after() returning(Object value): onMethodCall() {
		InteractionEntity interaction = InteractionBuilder.getInteraction();

		String methodName = thisJoinPoint.getStaticPart().getSignature().getName();
		boolean isEnum = thisJoinPoint.getSourceLocation().getWithinType().isEnum();

		if (!methodName.contains("$") && !isEnum) {
			Method method = ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
			String typeName = method.getReturnType().getName();

			LocationEntity currentLocation = InteractionBuilder.getCurrentLocation();

			if (!"void".equals(typeName)) {
				ArrayList<ParameterEntity> outputs = new ArrayList<ParameterEntity>();

				ParameterEntity parameter = new ParameterEntity();

				parameter.setName(null);
				parameter.setType(typeName);
				if (value == null) {
					parameter.setValue("null");
				} else {
					parameter.setValue(gson.toJson(value));
				}

				outputs.add(parameter);

				currentLocation.setOutputs(outputs);
			}

			InteractionBuilder.stack.pop();
			InteractionBuilder.setCurrentLocation(currentLocation.getParent());

			if (InteractionBuilder.stack.size() == 0) {

				if ("".equals(interaction.getStatus())) {
					interaction.addStatus("Ok");
				}

				try {
					LogSense.log(interaction);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			//			InnerLocationEntity current = (InnerLocationEntity) InteractionBuilder.getCurrentLocation();
			//			InnerLocationEntity parent = (current == null) ? null : (InnerLocationEntity) current.getParent();
			//
			//			System.out.println(String.format("after: %s ~ %s", (current == null) ? "null" : current.getRoutine(), (parent == null) ? "null" : parent.getRoutine()));
			System.out.println(gson.toJson(InteractionBuilder.stack));
		}
	}
}
