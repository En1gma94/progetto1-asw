package asw.machine;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.logging.Logger; 

@RestController
@PropertySource(value={"classpath:application.yml"})
public class ControllerS2 {
	
	// servizio S2 attivo sulla porta 8082

	@Value("${sales}")
	private String vendite;

	private final Logger logger = Logger.getLogger("asw.machine"); 

	/* servizio fornito da S2*/
	/* restituisce il numero dei modelli venduti dalla casa
	automobilistica nel paese specificato, nel caso in cui l'input sia 
	"Fiat" e "Italia" o "Chevrolet" e "USA" l'output generato 
	non è casuale */
	@RequestMapping("/S2/{casa_automobilistica}/{paese}")
	public String getVendite(@PathVariable String casa_automobilistica, @PathVariable String paese) {
		String[] venditeArray = vendite.split(",");
		String vendita;

		switch(casa_automobilistica) {
		case "Fiat":		switch(paese) {
							case "Italia": 	vendita = venditeArray[2]; break;
							default: 		int i = (int) (Math.round(Math.random()*(venditeArray.length-1)));
											vendita = venditeArray[i];
											break;
							}
							break;
							
		case "Chevrolet":	switch(paese) {
							case "USA": vendita = venditeArray[3]; break;
							default: 	int i = (int) (Math.round(Math.random()*(venditeArray.length-1)));
										vendita = venditeArray[i];
										break;
							}
							break;
							
		default: 			int i = (int) (Math.round(Math.random()*(venditeArray.length-1)));
							vendita = venditeArray[i];
							break;
		}

		logger.info("getVendite(casa_automobilistica,paese): " + vendita); 
		return vendita; 
	}

	/* servizio fornito da S2*/
	/* restituisce il numero dei modelli totali venduti dalla casa
	automobilistica specificata, nel caso in cui l'input sia "Fiat" 
	o "Chevrolet" l'output generato non è casuale */
	@RequestMapping("/S2/{casa_automobilistica}")
	public String getTotalSales(@PathVariable String casa_automobilistica) {
		String[] venditeArray = vendite.split(",");
		String vendita;

		switch(casa_automobilistica) {
		case "Fiat":		vendita = venditeArray[0];
		break;
		case "Chevrolet":	vendita = venditeArray[1];
		break;
		default: 			int i = (int) (Math.round(Math.random()*(venditeArray.length-1)));
		vendita = venditeArray[i];
		break;
		}

		logger.info("getTotalSales(casa_automobilistica): " + vendita); 
		return vendita; 
	}

}
