/**
 * Fichier principal pour le probleme
 * @author Tarek Ould Bachir (remplacer par votre nom)
 * @date : 2013-01-17
 */

public class Main 
{
	/**
	 * Fonction principale
	 * @param args (non utilise)
	 */
	public static void main(String[] args) 
	{
		/**
		 * Exercice 1
		 */
		
		PixelMap pmc = new PixelMap("/Users/pacomebondet/Documents/Work/Poly 2014-2015/H15/INF2010/TP1/ed.ppm");
		PixelMap pmg = pmc.toGrayImage();
		PixelMap pmb = pmc.toBWImage();
		
		
		String wName = "Edsger Dijkstra (original)";
		new DisplayImageWindow(wName, pmc, 50, 50);
		
		wName = "Edsger Dijkstra (gris)";
		new DisplayImageWindow(wName, pmg, 50+50, 50+50);
		
		wName = "Edsger Dijkstra (B&W)";
		new DisplayImageWindow(wName, pmb, 50+100, 50+100);
		
		
		/**
		 * Exercice 2
		 */
		
		PixelMapPlus pmp = new PixelMapPlus("/Users/pacomebondet/Documents/Work/Poly 2014-2015/H15/INF2010/TP1/ed.ppm");
		PixelMapPlus hpmp = new PixelMapPlus( pmp );		
		hpmp.halveHW();
		//hpmp.resize(hpmp.getHeight()-20, hpmp.getWidth() -20);
		PixelMapPlus gpmp = new PixelMapPlus( hpmp );		
		gpmp.convertToGrayImage();
		PixelMapPlus bwpmp = new PixelMapPlus( hpmp );		
		bwpmp.convertToBWImage();
		PixelMapPlus npmp = new PixelMapPlus( hpmp );		
		npmp.negate();
		
		pmp.inset(hpmp, 0, 0);
		pmp.inset(gpmp, pmp.getHeight()/2, 0);
		pmp.inset(bwpmp, 0, pmp.getWidth()/2);
		pmp.inset(npmp, pmp.getHeight()/2, pmp.getWidth()/2);
		
		wName = "Edsger Dijkstra";
		new DisplayImageWindow(wName, pmp);
                
		
	}
}
