package asw.machine;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.logging.Logger; 

@RestController
@PropertySource(value={"classpath:application.yml"})
public class ControllerS1 {
	
	// servizio S1 attivo sulla porta 8081

	@Value("${years}")
	private String anni;
	
	@Value("${places}")
	private String luoghi;
		
	private final Logger logger = Logger.getLogger("asw.machine"); 

	/* servizio fornito da S1*/
	/* restituisce le informazioni casuali relative ad una casa
	automobilistica, quali data e luogo di fondazione,
	nel caso in cui l'input sia "Fiat" o "Chevrolet" l'output generato 
	non è casuale */
	@RequestMapping("/S1/{casa_automobilistica}")
	public String getInfo(@PathVariable String casa_automobilistica) {
		String[] anniArray = anni.split(",");
		String[] luoghiArray = luoghi.split(",");
		String anno,luogo;
		
		switch(casa_automobilistica) {
		case "Fiat":		anno = anniArray[4];
					 	  	luogo = luoghiArray[1];
					 	  	break;
		case "Chevrolet":	anno = anniArray[0];
		 			 		luogo = luoghiArray[3];
		 			 		break;
		default: 			int i = (int) (Math.round(Math.random()*(anniArray.length-1)));
							anno = anniArray[i];
							luogo = luoghiArray[i];
							break;
		}
		
		String output = anno + " a " + luogo;
		logger.info("getInfo(casa_automobilistica): " + output); 
		return output; 
	}
	
}
