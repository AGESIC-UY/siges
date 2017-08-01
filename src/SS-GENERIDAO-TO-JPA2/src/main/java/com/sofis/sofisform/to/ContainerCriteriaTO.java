/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public abstract class ContainerCriteriaTO extends CriteriaTO implements Serializable{

    public abstract List<CriteriaTO> getCriterias();
}
