/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.utils;

import java.util.Comparator;
import javax.faces.model.SelectItem;

/**
 *
 * @author bruno
 */
public class SelectItemComparator implements Comparator<SelectItem> {

	@Override
	public int compare(SelectItem o1, SelectItem o2) {
		return o1.getLabel().compareTo(o2.getLabel());
	}

}
