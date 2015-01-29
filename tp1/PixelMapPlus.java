/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : Tarek Ould Bachir (remplacer par votre nom)
 * @date   : 2011-09-10
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imageData[i][j] = imageData[i][j].Negative();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imageData[i][j] = imageData[i][j].toBWPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imageData[i][j] = imageData[i][j].toGrayPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imageData[i][j] = imageData[i][j].toColorPixel();
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(boolean clockWise)
	{
        double angle = Math.toRadians(clockWise ? -10.0 : 10.0);
        AbstractPixel[][] newData = new AbstractPixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int xsrc = (int)(i * Math.cos(angle) + j * Math.sin(angle));
                int ysrc = (int)(i * Math.sin(angle) * -1 + j * Math.cos(angle));
                if(xsrc >= 0 && xsrc < height && ysrc >= 0 && ysrc < width){
                    newData[i][j] = imageData[xsrc][ysrc];
                } else {
                    newData[i][j] = new BWPixel(true);
                }
            }
        }
        imageData = newData;
	}
	
	/**
	 * Reduit de moitie la longueur et la largeur de l'image 
	 * @param clockWise : Direction de la rotation 
	 */
	public void halveHW()
	{
            AbstractPixel[][] newData = new AbstractPixel[height / 2][width / 2];
            for (int i = 0; i < height; i+=2) {
                for (int j = 0; j < width; j+=2) {
                    newData[i / 2][j / 2] = imageData[i][j];
                }
            }
            height /= 2;
            width /= 2;
            imageData = newData;
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
            if (row0 >= height || col0 >= width) {return;}
            for (int i = 0; i < pm.height && i < height; i++) {
                for (int j = 0; j < pm.width && j < width; j++) {
                    imageData[i + row0][j + col0] = pm.imageData[i][j];
                }
            }
	}
	
	public void resize(int h, int w)
    {
		AbstractPixel[][] newData = new AbstractPixel[h][w];
	    for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (i < height && i < h && j < width && j < w) {
			    	newData[i][j] = imageData[i][j];
				} else {
			    	newData[i][j] = new BWPixel(true);
				}
		 	}
	    }
	    height = h;
	    width = w;
	    imageData = newData;
      }
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		AbstractPixel[][] newData = new AbstractPixel[height][width];
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	if (i - rowOffset < 0 || i - rowOffset >= height || j - colOffset < 0 || j - colOffset >= height) {
					newData[i][j] = new BWPixel(true);
				} else {
					newData[i][j] = imageData[i - rowOffset][j - colOffset];
				}
            }
        }
        imageData = newData;
	}
}
