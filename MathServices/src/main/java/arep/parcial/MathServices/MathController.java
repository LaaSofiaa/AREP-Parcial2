package arep.parcial.MathServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MathController {
    @GetMapping("/collatzsequence")
    public Map<String,Object> collatz(@RequestParam Integer value){
        List<Integer>secuencia= new ArrayList<>();
        collatzSequence(value,secuencia);

        Map<String,Object>response= new LinkedHashMap<>();
        response.put("operation ", "collatzsequence");
        response.put("input ", value);
        response.put("output ", secuencia);
        System.out.println(secuencia);
        System.out.println(response);
        return response;

    }

    private void collatzSequence(Integer n, List<Integer> secuencia) {
        secuencia.add(n);
        if(n==1){
            return;
        }else if(n%2==0){
            collatzSequence(n/2,secuencia);
        }else{
            collatzSequence(n*3+1,secuencia);
        }
    }

}
