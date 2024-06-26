import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;

public class ObraDeArte {
  
  private static final int ANCHO = 600;
  private static final int ALTO = 600;
  private static final int MIN = 0;
  private static final int MAX = 6;
  private static final int SUBMATRIZ = 1;
  private int[][] dibujo = new int [ANCHO][ALTO];// z-buffer
  
  JFrame f = null;
  
  private BufferedImage imagen = null;
  
  public ObraDeArte(){
    
    imagen = new BufferedImage(ANCHO,ALTO,BufferedImage.TYPE_INT_RGB);
    
    //getDibujo();
    //dibujarDiagonal();
    dibujarFila(200, 0, 600,5,0);
    dibujarFila(400, 0, 600,5,1);
    //dibujarFila(200,200, 400, 10,10);

    dibujarColumna(200,0, 600, 5,1);
    dibujarColumna(400,0, 600, 5,1);

    for(int i=0;i<ANCHO;i++){
      for(int j=0;j<ALTO;j++){
        
        switch (dibujo[i][j]) {
          case 0:
            RGB rgb = new RGB(47,237,53);
            imagen.setRGB(i, j, rgb.calcularValorFuncional());
            break;
          case 1:
            imagen.setRGB(i, j, Color.BLACK.getRGB());
            break;
          case 2:
            imagen.setRGB(i, j, Color.RED.getRGB());
            break;
          case 3:
            imagen.setRGB(i, j, Color.BLUE.getRGB());
            break;
          case 4:
            imagen.setRGB(i, j, Color.YELLOW.getRGB());
            break;
          default:
            imagen.setRGB(i, j, Color.GREEN.getRGB());
        } //switch
      } //for
    } //for
    
    f = new JFrame("Mi obra de arte"){
      public void paint(java.awt.Graphics g){
        g.drawImage(imagen,0,0,null);
      } //paint
    }; //jframe
      
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(ANCHO,ALTO);
    f.repaint();
    f.setVisible(true);
  }// constructor
  
  public void getDibujo(){
    dibujo = new int[ANCHO][ALTO];
    int aleatorio = 0;
    //for(int i=0;i<ANCHO;i+=SUBMATRIZ){
      for(int j=0;j<ALTO;j+=SUBMATRIZ){
        aleatorio = MIN + (int)(Math.random() * (MAX - MIN));
        for (int k = 0; k < ANCHO; k++) {
          for (int m = 0; m < SUBMATRIZ; m++) {
            dibujo[k][j+m] = aleatorio; 
          }
        }
      } //for 
    //}
  } //getdibujo

  public void dibujarDiagonal(){
    for(int i = 0; i < ANCHO; i++){
      dibujo[i][i] = 3;
    }
  }

  public int clamp(int limInf, int limSup, int valor){
    int resultado = valor;
    if(valor < limInf){
      resultado = limInf;
    }
    else if(valor > limSup){
      resultado = limSup;
    }
    return resultado;
  }

  public void dibujarFila(int f, int desde, int hasta, int grosor,int color){
    // si desde,hasta es menor que 0, lo convertimos en 0
    // si desde,hasta es mayor que el ancho lo convertimos al ancho
    desde = clamp(0,ANCHO, desde);
    hasta = clamp(0,ANCHO, hasta);
    for(int j = 0; j < grosor; j++){
      for(int i = desde; i <  hasta; i++){
        dibujo[i][f+j] = color; 
      }
    }  
  }

  public void dibujarColumna(int c, int desde, int hasta, int grosor,int color){
    desde = clamp(0,ALTO, desde);
    hasta = clamp(0,ALTO, hasta);
    for(int j = 0; j < grosor; j++){
      for(int i = desde; i <  hasta; i++){
        dibujo[c+j][i] = color; 
      }
    }  
  }
  
  public static void main(String[] args){
    ObraDeArte o = new ObraDeArte();
  }
}

