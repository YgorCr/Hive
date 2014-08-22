package business.controllers;

import infra.DaoAbstractFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import util.DataDeValidadeException;
import util.PrecoException;
import util.StructureException;
import business.model.IngressoAB;

public class IngressoController implements IngressoControllerIF {

	public Long create(HashMap<String, Object> objeto) throws PrecoException,
			DataDeValidadeException, StructureException {
		validaValor(objeto.get("valor"));
		validaDataDeValidade(objeto.get("dataDeValidade"));
		IngressoAB i;
		try {
			i = (IngressoAB) DaoAbstractFactory.getInstance(
					IngressoAB.class).create(objeto);
		} catch (IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao criar ingresso");// TODO Auto-generated catch block
		}
		return i.getId();
	}

	public void update(Long id, HashMap<String, Object> objeto)
			throws PrecoException, DataDeValidadeException {
		validaValor(objeto.get("valor"));
		validaDataDeValidade(objeto.get("dataDeValidade"));

		try {
			DaoAbstractFactory.getInstance(IngressoAB.class).update(id, objeto);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Long id) {
		DaoAbstractFactory.getInstance(IngressoAB.class).delete(id);
	}

	public IngressoAB get(Long id) throws StructureException {
		HashMap<String, Object> query = new HashMap<String, Object>();
		query.put("id", id);
		try {
			return (IngressoAB) DaoAbstractFactory.getInstance(IngressoAB.class)
					.findOneBy(query);
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao recuperar ingresso");// TODO Auto-generated catch block
		}
	}

	public String geraQRCode(String codigo) {
		CodigoIF gerador = new CodigoAdapter();

		return gerador.geraQRCode(codigo);
	}

	public void validaValor(Object valor) throws PrecoException {

		if ((Double) valor < 0) {
			throw new PrecoException("O Valor do Ingresso deve ser positivo!");
		}

	}

	public void validaDataDeValidade(Object dataDeValidade1)
			throws DataDeValidadeException { // determinar hora minuto e segundo
												// da validade de um ingresso
		Calendar dataDeValidade = (Calendar) dataDeValidade1;
		if ((dataDeValidade != null)
				&& dataDeValidade.before(Calendar.getInstance())) {
			throw new DataDeValidadeException(
					"A Data de Validade do Ingresso já foi ultrapassada!");
		}
	}

	@Override
	public IngressoAB[] listAll(Long offset, Long max) throws StructureException {
		try {
			return (IngressoAB[]) DaoAbstractFactory.getInstance(IngressoAB.class)
					.listAll(offset, max).toArray();
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao listar ingressos");// TODO Auto-generated catch block
		}
	}

	@Override
	public IngressoAB[] listAll() {
		// System.out.println("a\n"+"a\n"+"a\n"+"a\n"+"a\n"+"a\n"+"a\n"+"a\n"+"a\n");
		Object ar[] = null;
		try {
			ar = DaoAbstractFactory.getInstance(IngressoAB.class)
					.listAll().toArray();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(Arrays.copyOf(ar, ar.length, IngressoAB[].class)
		// == null);
		return Arrays.copyOf(ar, ar.length, IngressoAB[].class);
	}

	public HashMap<String, Object> toHashMap(IngressoAB obj) {
		HashMap<String, Object> objMap = new HashMap<>();

		for (Field f : IngressoAB.class.getDeclaredFields()) {
			String metodo = "get" + f.getName().substring(0, 1).toUpperCase()
					+ f.getName().substring(1);
			try {
				objMap.put(f.getName(),
						IngressoAB.class.getMethod(metodo, new Class[0])
								.invoke(obj, new Object[0]));
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
		}

		return objMap;
	}

	public List<HashMap<String, Object>> listToHashMap(IngressoAB[] list) {
		List<HashMap<String, Object>> res = new ArrayList<HashMap<String, Object>>(
				list.length);

		for (IngressoAB user : list) {
			res.add(this.toHashMap(user));
		}

		return res;
	}
}
