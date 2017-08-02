package uy.gub.agesic.pge.core.ws.addressing;

import java.util.List;

public interface ElementExtensible extends AddressingType {
	public List<Object> getElements();

	public void addElement(Object element);

	public boolean removeElement(Object element);

}