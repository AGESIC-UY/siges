
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bruno
 */
public class TestDependenciaAlgoritmo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String param = "1:-4,5:6,6:,7";
        List<Integer[]> dependenciasList = new ArrayList<>();
        String[] dependencias = param.split(",");
        String[] de;
        Integer[] elem;
        for (String d : dependencias) {
            de = d.split(":");
            Integer entPreId = Integer.parseInt(de[0]);
            Integer dias = de.length > 1 && !StringsUtils.isEmpty(de[1]) ? 
                    Integer.parseInt(de[1]) : 
                    0;
            elem = new Integer[2];
            elem[0] = entPreId;
            elem[1] = dias;
            dependenciasList.add(elem);
        }
        
        for(Integer[] i : dependenciasList){
            System.out.println("["+ i[0] +"|"+ i[1] +"]");
        }
    }

}
