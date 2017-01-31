import weka.core.Instances;
import weka.core.Instance;
import weka.core.SparseInstance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;

import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;

public class Inst {

	public static void main(String args[]) throws Exception {

		String dataFile = "imageData.arff";
		Picture pic = new Picture(args[0]);
		Pixel[] pixels = pic.getPixels();
		PrintWriter bfwrite = new PrintWriter(dataFile, "utf-8");//used to create the arff file
		bfwrite.println("@relation image");
		bfwrite.println("@attribute red numeric");
		bfwrite.println("@attribute green numeric");
		bfwrite.println("@attribute blue numeric");
		bfwrite.println("@data");
		for (Pixel p : pixels) {
			bfwrite.println(p.getRed() + "," + p.getGreen() + "," + p.getBlue());//writing data in arff file
		}
		bfwrite.close();
		BufferedReader fRead = new BufferedReader(new FileReader(dataFile));
		Instances ins = new Instances(fRead);
		SimpleKMeans kmeans = new SimpleKMeans();
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(5);
		kmeans.buildClusterer(ins);//build clusters
		int[] clusters = kmeans.getAssignments();//get information about which pixel is assigned which cluster
		Instances kl = kmeans.getClusterCentroids();
		int[][] array = new int[5][3];
		int c = 0;
		Iterator l = kl.iterator();
		System.out.println("Centroids that we got:");
		while (l.hasNext()) {
			String s = l.next().toString();
			String[] s2 = s.split(",");
			String[] s3 = s2[0].split("\\.");
			array[c][0] = (int) Long.parseLong(s3[0]);
			s3 = s2[1].split("\\.");
			array[c][1] = (int) Long.parseLong(s3[0]);
			s3 = s2[2].split("\\.");
			array[c][2] = (int) Long.parseLong(s3[0]);
			c++;
			System.out.println(s);//centroid that we get
		}
		for (int i = 0; i < clusters.length; i++) {
			pixels[i].setRed(array[clusters[i]][0]);
			pixels[i].setGreen(array[clusters[i]][1]);
			pixels[i].setBlue(array[clusters[i]][2]);
		}
		//assigning targeted color to clusters
		array[0][0] = 255;
		array[0][1] = 255;
		array[0][2] = 255;
		array[1][0] = 0;
		array[1][1] = 0;
		array[1][2] = 0;
		array[2][0] = 255;
		array[2][1] = 0;
		array[2][2] = 0;
		array[3][0] = 0;
		array[3][1] = 255;
		array[3][2] = 0;
		array[4][0] = 0;
		array[4][1] = 0;
		array[4][2] = 255;
		//assigning target colors to pixels.
		for (int i = 0; i < clusters.length; i++) {
			pixels[i].setRed(array[clusters[i]][0]);
			pixels[i].setGreen(array[clusters[i]][1]);
			pixels[i].setBlue(array[clusters[i]][2]);
			
		}

		pic.show();

	}

}