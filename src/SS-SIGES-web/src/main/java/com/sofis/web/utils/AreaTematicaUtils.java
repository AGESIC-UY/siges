package com.sofis.web.utils;

import com.sofis.entities.data.AreasTags;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import org.icefaces.ace.model.tree.NodeState;
import org.icefaces.ace.model.tree.NodeStateMap;

public abstract class AreaTematicaUtils {

	public static final String AREAS_TEMATICAS_STATE_MAP = "areasTematicasStateMap";
	public static final String LISTA_AREAS_TAG_TREE_NODE = "listaAreasTagsTreeNode";

	/*
	 * @param listaAreasTags Lista de AreasTags a desplegar en el árbol.
	 * @param listaAreasTagsTreeNode Arbol de AreasTags.
	 * @param areasTematicas Áreas temáticas seleccionadas.
	 */
	public static Map<String, Object> setNodosForAreaTematica(List<AreasTags> listaAreasTags,
			List<MutableTreeNode> listaAreasTagsTreeNode, Set<AreasTags> areasTematicas, boolean aplicarDeshabilitacion) {

		NodeStateMap areasTematicasStateMap = new NodeStateMap();

		for (AreasTags aTags : listaAreasTags) {
			if (aTags.getAreatagPadreFk() != null && aTags.getAreatagPadreFk().getArastagPk() != null) {
				continue;
			}

			DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode();
			nodoPadre.setAllowsChildren(true);

			nodoPadre.setUserObject(aTags);
			nodoPadre = setNodosHijosAreaTematica(listaAreasTags, nodoPadre, aTags, areasTematicas, areasTematicasStateMap, aplicarDeshabilitacion);

			areasTematicasStateMap = setAreasTemStateMap(areasTematicas, aTags, nodoPadre, areasTematicasStateMap, aplicarDeshabilitacion);
			listaAreasTagsTreeNode.add(nodoPadre);
		}

		Map<String, Object> result = new HashMap<>();
		result.put(AREAS_TEMATICAS_STATE_MAP, areasTematicasStateMap);
		result.put(LISTA_AREAS_TAG_TREE_NODE, listaAreasTagsTreeNode);

		return result;
	}

	private static DefaultMutableTreeNode setNodosHijosAreaTematica(List<AreasTags> listaAreasTags,
			DefaultMutableTreeNode nodoPadre, AreasTags areasTagsPadre, Set<AreasTags> areasTematicas,
			NodeStateMap areasTematicasStateMap, boolean aplicarDeshabilitacion) {

		if (nodoPadre == null || areasTagsPadre == null) {

			return nodoPadre;
		}

		for (AreasTags aTags : listaAreasTags) {
			if (aTags.getArastagPk() != null
					&& aTags.getAreatagPadreFk() != null
					&& aTags.getAreatagPadreFk().getArastagPk().equals(areasTagsPadre.getArastagPk())) {

				DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode();
				nodoHijo.setAllowsChildren(true);
				nodoHijo.setUserObject(aTags);

				setNodosHijosAreaTematica(listaAreasTags, nodoHijo, aTags, areasTematicas, areasTematicasStateMap, aplicarDeshabilitacion);

				areasTematicasStateMap = setAreasTemStateMap(areasTematicas, aTags, nodoHijo, areasTematicasStateMap, aplicarDeshabilitacion);
				nodoPadre.add(nodoHijo);
			}
		}

		return nodoPadre;
	}

	private static NodeStateMap setAreasTemStateMap(Set<AreasTags> areasTematicas,
			AreasTags areaT, DefaultMutableTreeNode nodo, NodeStateMap areasTematicasStateMap, boolean aplicarDeshabilitacion) {

		NodeState ns = new NodeState();

		if (areasTematicas != null && !areasTematicas.isEmpty()
				&& areasTematicas.contains(areaT)) {
			ns.setSelected(true);
		}

		ns.setSelectionEnabled(!aplicarDeshabilitacion || areaT.getHabilitada() || ns.isSelected());

		areasTematicasStateMap.put(nodo, ns);

		return areasTematicasStateMap;
	}
}
