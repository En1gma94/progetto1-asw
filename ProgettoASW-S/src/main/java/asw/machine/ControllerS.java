package asw.machine;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.logging.Logger; 

@RestController
@PropertySource(value={"classpath:application.yml"})
public class ControllerS {
	
	// servizio S attivo sulla porta 8080

	private final Logger logger = Logger.getLogger("asw.machine"); 
	
	@Value("${machine.informazioni.uri}") 
	private String infoUri;

	@Value("${machine.venditeTotali.uri}") 
	private String vendTotaliUri;

	@Value("${machine.venditePaese.uri}") 
	private String vendPaeseUri;

	/*servizio fornito da S nel caso in cui venga specificata solo la 
	casa automobilistica*/
	@RequestMapping("/S/{casa_automobilistica}")
	public String getMachineTotalSold(@PathVariable String casa_automobilistica) {
		// richiesta del servizio S1
		String fond = getInfo(casa_automobilistica);
		// richiesta del servizio S2
		String vend = getModelliTotali(casa_automobilistica);
		logger.info("getMachineTotalSold(casa_automobilistica): " + fond + vend);
		String output = "La "+ casa_automobilistica + " è stata fondata il " +fond+ " e sono stati venduti " + vend + " modelli in totale";
		return output; 
	}

	/*servizio fornito da S nel caso in cui vengano specificati casa 
	automobilistica e paese di interesse */
	@RequestMapping("/S/{casa_automobilistica}/{paese}")
	public String getMachineCountrySold(@PathVariable String casa_automobilistica,@PathVariable String paese) {
		// richiesta del servizio S1
		String fond = getInfo(casa_automobilistica);
		// richiesta del servizio S2
		String vend = getModelliPaese(casa_automobilistica,paese); 
		logger.info("getMachineCountrySold(casa_automobilistica,paese): " + fond + vend);
		String output = "La "+ casa_automobilistica + " è stata fondata il " +fond+ " e sono stati venduti " + vend + " modelli in "+paese;
		return output; 
	}

	private String getMachineCasaPaese(String uri,String casa_automobilistica, String paese) {
		return new RestTemplate().getForObject(uri,String.class,casa_automobilistica,paese);
	}
	
	private String getMachineCasa(String uri,String casa_automobilistica) {
		return new RestTemplate().getForObject(uri,String.class,casa_automobilistica);
	}
	
	/*richiede a S1 di fornire le informazioni
	(data e luogo di fondazione) della casa automobilistica 
	specificata*/
	private String getInfo(String casa_automobilistica) {
		return getMachineCasa(infoUri,casa_automobilistica);
	}

	/*richiede a S2 di fornire il numero dei modelli totali venduti 
	dalla casa automobilistica specificata*/
	private String getModelliTotali(String casa_automobilistica) {
		return getMachineCasa(vendTotaliUri,casa_automobilistica);
	}

	/*richiede a S2 di fornire il numero dei modelli totali venduti 
	dalla casa automobilistica specificata in un determinato paese*/
	private String getModelliPaese(String casa_automobilistica,String paese) {
		return getMachineCasaPaese(vendPaeseUri,casa_automobilistica,paese);
	}

}
