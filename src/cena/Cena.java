package cena;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Kakugawa
 */
public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private TextRenderer textRenderer;

    public float xBastao;

    public int variavelX;

    public float ang;
    public int op;
    
    //dados do cubo
    public float size;

    //dados da esfera
    public float radio;
    public int slices;
    public int stacks;

    //dados do cone
    public float height;

    //dados do torus
    public float innerRadius;
    public float outerRadius;

    public float xBola;

    public float yBola;

    public float yBastao;

    public float bastaoParte1;
    public float bastaoParte2;
    public float bastaoParte3;

    public float bastaoParte4;

    public float bastaoParte5;

    public float bastaoParte6;

    public float ladoEsquerdoBastao;
    public float ladoDireitoBastao;

    public float alturaBastao;
    public int rings;

    public int variavelY;

    public int teto;

    public int paredeDireita;
    public int paredeEsquerda;


    //Preenchimento
    public int mode;  
    
    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena        
        GL2 gl = drawable.getGL().getGL2();        
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;  
        
        reset();
        
        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 15));
        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }
    
    public void reset(){
        teto = 95;
        paredeDireita = 95;
        paredeEsquerda = -95 ;

        ang = 0;        
        
        //dados do cubo
        size = 25;


        
        //dados da esfera
        radio = 5;
        slices = 15;
        stacks = 15;

        //dados do cone
        height = 50;

        //dados do torus
        innerRadius = 10;
        outerRadius = 50;
        rings = 6;
        
        //preenchimento
        mode = GL2.GL_FILL;

        //bastao
        xBastao = 0f;
        yBastao = -80f;

        xBola = 0f;

        ladoEsquerdoBastao = xBastao - 27f;
        ladoDireitoBastao = xBastao + 27f;
        alturaBastao = -65f;




        variavelY = 1;
        variavelX = 0;






    }

    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();                
        //objeto para desenho 3D
        GLUT glut = new GLUT();
        
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(1, 1, 1, 1);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);       
        gl.glLoadIdentity(); //ler a matriz identidade
        
        /*
            desenho da cena        
        *
        */


        //Bastao
        gl.glColor3f(0,0,0.0f); //cor do objeto
        gl.glPushMatrix();
        gl.glTranslatef(xBastao,yBastao,0);
        gl.glScalef(2,1,1);
        glut.glutSolidCube(size);
        gl.glPopMatrix();
        ladoEsquerdoBastao = xBastao - 27f;
        ladoDireitoBastao = xBastao + 27f;

        bastaoParte1 = ladoEsquerdoBastao;
        bastaoParte2 = bastaoParte1 + 11;
        bastaoParte3 = bastaoParte2 + 11 ;
        bastaoParte4 = bastaoParte3 + 11 ;
        bastaoParte5 = bastaoParte4 + 11 ;
        bastaoParte6 = bastaoParte5 + 11 ;

        //Bola
        gl.glColor3f(0,0,0); //cor do objeto
        gl.glPushMatrix();
        gl.glTranslatef(xBola, yBola,0);
        glut.glutSolidSphere(radio, slices, stacks);
        gl.glPopMatrix();


        
        //Modo de desenho - os parametros s�o constantes inteiras
        //int modo =  GL2.GL_FILL; ou GL2.GL_LINE ou GL2.GL_POINT        
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);
        
        System.out.println("op = " + op);

        String m = mode == GL2.GL_LINE ? "LINE" : "FILL";


        yBola = yBola - variavelY;
        xBola = xBola + variavelX;



        if(( ladoEsquerdoBastao < xBola && ladoDireitoBastao > xBola) && (alturaBastao == yBola) ) {
            variavelY = variavelY * -1;

            if(xBola > bastaoParte5 && xBola < bastaoParte6 ) {
                variavelX = 3;
            }
            if(xBola > bastaoParte4 && xBola < bastaoParte5 ) {
                variavelX = 1;
            }
            if(xBola > bastaoParte3 && xBola < bastaoParte4) {
                variavelX = 0;
            }
            if(xBola > bastaoParte2 && xBola < bastaoParte3) {
                variavelX = -1;
            }
            if(xBola > bastaoParte1 && xBola < bastaoParte2) {
                variavelX = -3;
            }

        }

        if(yBola > teto ){
            variavelY = variavelY * -1;
        }
        if(xBola > paredeDireita){
            variavelX = -1;
        }

        if(xBola <= paredeEsquerda){
            variavelX = 1;
        }


            desenhaTexto(gl, 20, 580, Color.BLACK ,"Modo: " + m);
        switch(op){            
            case 1:
                gl.glColor3f(0,0,0.8f); //cor do objeto
                gl.glPushMatrix();
                    gl.glRotated(ang, 0, 1, 1);
                    glut.glutSolidCube(size);
                gl.glPopMatrix();
                                
                desenhaTexto(gl, 390, 580, Color.BLACK , "Objeto: CUBO");
                desenhaTexto(gl, 390, 560, Color.BLACK , "Size: " + size);
                break;
            case 2:
                gl.glColor3f(0,0,0.8f); //cor do objeto
                gl.glPushMatrix();
                    gl.glRotated(ang, 0, 1, 1);
                    glut.glutSolidSphere(radio, slices, stacks);
                gl.glPopMatrix();
                              
                desenhaTexto(gl, 390, 580, Color.BLACK , "Objeto: ESFERA");
                desenhaTexto(gl, 390, 560, Color.BLACK , "Radio: " + radio);
                desenhaTexto(gl, 390, 540, Color.BLACK , "Slices: " + slices);
                desenhaTexto(gl, 390, 520, Color.BLACK , "Stacks: " + stacks);
                break;
            case 3:
                gl.glColor3f(0,0,0.8f); //cor do objeto
                gl.glPushMatrix();
                    gl.glRotated(ang, 0, 1, 1);
                    glut.glutSolidCone(radio, height, slices, stacks);
                gl.glPopMatrix();
                                
                desenhaTexto(gl, 390, 580, Color.BLACK , "Objeto: CONE");
                desenhaTexto(gl, 390, 560, Color.BLACK , "Radio: " + radio);
                desenhaTexto(gl, 390, 540, Color.BLACK , "Slices: " + slices);
                desenhaTexto(gl, 390, 520, Color.BLACK , "Stacks: " + stacks);
                desenhaTexto(gl, 390, 500, Color.BLACK , "Height: " + height);
                break;
            case 4:
                gl.glColor3f(0,0,0.8f); //cor do objeto
                gl.glPushMatrix();
                    gl.glRotated(ang, 0, 1, 1);
                    glut.glutSolidCylinder(radio, height, slices, stacks);
                gl.glPopMatrix();
                                
                desenhaTexto(gl, 390, 580, Color.BLACK , "Objeto: CILINDRO");
                desenhaTexto(gl, 390, 560, Color.BLACK , "Radio: " + radio);
                desenhaTexto(gl, 390, 540, Color.BLACK , "Slices: " + slices);
                desenhaTexto(gl, 390, 520, Color.BLACK , "Stacks: " + stacks);
                desenhaTexto(gl, 390, 500, Color.BLACK , "Height: " + height);
                break;                
            case 5:
                gl.glColor3f(0,0,0.8f); //cor do objeto
                gl.glPushMatrix();
                    gl.glRotated(ang, 0, 1, 1);
                    glut.glutSolidTorus(innerRadius, outerRadius, slices, rings);
                gl.glPopMatrix();
                                
                desenhaTexto(gl, 390, 580, Color.BLACK , "Objeto: TORUS");
                desenhaTexto(gl, 390, 560, Color.BLACK , "InnerRadius: " + innerRadius);
                desenhaTexto(gl, 390, 540, Color.BLACK , "OuterRadius: " + outerRadius);
                desenhaTexto(gl, 390, 520, Color.BLACK , "NSides: " + slices);
                desenhaTexto(gl, 390, 500, Color.BLACK , "Rings: " + rings);
                break;
            case 6:
                gl.glColor3f(0,0,0.8f); //cor do objeto
                gl.glPushMatrix();
                    gl.glRotated(ang, 0, 1, 1);
                    glut.glutSolidTeapot(size);
                gl.glPopMatrix();
                                
                desenhaTexto(gl, 390, 580, Color.BLACK ,"Objeto: TEAPOT");
                desenhaTexto(gl, 390, 560, Color.BLACK , "Size: " + size);
                break;            
        }    
        

        
        gl.glFlush();      
    }
    
    public void desenhaTexto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase){         
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);       
        textRenderer.setColor(cor);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {    
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();  
                
        //ativa a matriz de projecao
        gl.glMatrixMode(GL2.GL_PROJECTION);      
        gl.glLoadIdentity(); //ler a matriz identidade

        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);
        
        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //ler a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }    
       
    @Override
    public void dispose(GLAutoDrawable drawable) {}


}
