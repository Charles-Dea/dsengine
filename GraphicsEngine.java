// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class GraphicsEngine extends JPanel {
   private Dictionary go;
   private HashMap<String, BufferedImage> lt;
   private Main m;

   private BufferedImage getImage(String var1) {
      if (this.lt.containsKey(this.m.abspth(var1))) {
         return (BufferedImage)this.lt.get(var1);
      } else {
         BufferedImage var2;
         try {
            var2 = ImageIO.read(new File(var1));
         } catch (Exception var4) {
            System.err.println("Could not load " + var1);
            return null;
         }

         this.lt.put(var1, var2);
         return var2;
      }
   }

   protected void paintComponent(Graphics var1) {
      Graphics2D var2 = (Graphics2D)var1;
      ArrayList<Variable> var3 = new ArrayList();
      Iterator var4 = this.go.values().iterator();

      while(var4.hasNext()) {
         Variable var5 = (Variable)var4.next();
         if (var5.t == Variable.Type.DICTIONARY) {
            var3.add((Dictionary)var5);
         }
      }

      var3.sort((var0, var1x) -> {
         Vstring vvar2 = new Vstring("z");
         Variable vvar3 = ((Dictionary)var0).get(vvar2);
         double vvar4;
         if (vvar3.t == Variable.Type.NUMBER) {
            Number var6 = (Number)vvar3;
            vvar4 = var6.v;
         } else {
            System.err.print("Game object z position is not a number\n");
            System.exit(-1);
            vvar4 = 0.0;
         }

         Variable var10 = ((Dictionary)var0).get(vvar2);
         double var7;
         if (var10.t == Variable.Type.NUMBER) {
            Number var9 = (Number)var10;
            var7 = var9.v;
         } else {
            System.err.print("Game object z position is not a number\n");
            System.exit(-1);
            var7 = 0.0;
         }

         return (int)(vvar4 - var7);
      });
      Vstring var27 = new Vstring("x");
      Vstring var28 = new Vstring("y");
      Vstring var6 = new Vstring("w");
      Vstring var7 = new Vstring("h");
      Vstring var8 = new Vstring("t");

      double var12;
      double var15;
      double var18;
      double var21;
      BufferedImage var24;
      for(Iterator var9 = var3.iterator(); var9.hasNext(); var2.drawImage(var24.getScaledInstance((int)var18, (int)var21, 1), (int)var12, (int)var15, (ImageObserver)null)) {
         Dictionary var10 = (Dictionary)var9.next();
         Variable var11 = var10.get(var27);
         var12 = 0.0;
         if (var11.t == Variable.Type.NUMBER) {
            Number var14 = (Number)var11;
            var12 = var14.v;
         } else {
            System.err.print("Game object x position is not a number\n");
            System.exit(-1);
         }

         Variable var29 = var10.get(var28);
         var15 = 0.0;
         if (var29.t == Variable.Type.NUMBER) {
            Number var17 = (Number)var29;
            var15 = var17.v;
         } else {
            System.err.print("Game object y position is not a number\n");
            System.exit(-1);
         }

         Variable var30 = var10.get(var6);
         var18 = 0.0;
         if (var30.t == Variable.Type.NUMBER) {
            Number var20 = (Number)var30;
            var18 = var20.v;
         } else {
            System.err.print("Game object w position is not a number\n");
            System.exit(-1);
         }

         Variable var31 = var10.get(var7);
         var21 = 0.0;
         if (var11.t == Variable.Type.NUMBER) {
            Number var23 = (Number)var31;
            System.exit(-1);
         } else {
            System.err.print("Game object h position is not a number\n");
            System.exit(-1);
         }

         Variable var26 = var10.get(var8);
         var24 = null;
         if (var26.t == Variable.Type.STRING) {
            Vstring var25 = (Vstring)var26;
            var24 = this.getImage(var25.v);
         } else {
            System.err.print("Game object t position is not a string\n");
            System.exit(-1);
         }
      }

   }

   public GraphicsEngine(Dictionary var1, Main var2) {
      this.go = var1;
      this.lt = new HashMap();
      this.m = var2;
   }
}