package ohtu;

import ohtu.verkkokauppa.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        Kauppa kauppa = (Kauppa) ctx.getBean("kauppa");
        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma : kauppa.getVarasto().getKirjaapitava().getTapahtumat()) {
            System.out.println(tapahtuma);
        }
				for (int i = 0; i < 10; i++) {
					for(int k =0 ; k < 10; k++) {
					for (int j = 0; j < 10; j++) {
						System.out.println("Naminami");
					}
					}
				}
			if (0 == 1) {
				if (1 == 0) {
					if (2==3) {
						System.out.println("Numinumi");
					}
				}
			}
			System.out.println("Naminami");
			System.out.println("Naminami");
			System.out.println("Naminami");
			System.out.println("Naminami");
			System.out.println("Naminami");
			System.out.println("Naminami");
			System.out.println("Naminami");
    }
	public static void metodi() {
		if (2==0) {
			
		}
		if (2==0) {
			
		}if (2==0) {
			
		}if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}if (2==0) {
			
		}if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
		if (2==0) {
			
		}
	}}
