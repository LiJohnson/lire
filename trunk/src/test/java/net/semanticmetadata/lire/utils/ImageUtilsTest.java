/*
 * This file is part of the LIRE project: http://www.semanticmetadata.net/lire
 * LIRE is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * LIRE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LIRE; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * We kindly ask you to refer the any or one of the following publications in
 * any publication mentioning or employing Lire:
 *
 * Lux Mathias, Savvas A. Chatzichristofis. Lire: Lucene Image Retrieval –
 * An Extensible Java CBIR Library. In proceedings of the 16th ACM International
 * Conference on Multimedia, pp. 1085-1088, Vancouver, Canada, 2008
 * URL: http://doi.acm.org/10.1145/1459359.1459577
 *
 * Lux Mathias. Content Based Image Retrieval with LIRE. In proceedings of the
 * 19th ACM International Conference on Multimedia, pp. 735-738, Scottsdale,
 * Arizona, USA, 2011
 * URL: http://dl.acm.org/citation.cfm?id=2072432
 *
 * Mathias Lux, Oge Marques. Visual Information Retrieval using Java and LIRE
 * Morgan & Claypool, 2013
 * URL: http://www.morganclaypool.com/doi/abs/10.2200/S00468ED1V01Y201301ICR025
 *
 * Copyright statement:
 * ====================
 * (c) 2002-2013 by Mathias Lux (mathias@juggle.at)
 *  http://www.semanticmetadata.net/lire, http://www.lire-project.net
 *
 * Updated: 01.07.13 16:56
 */

package net.semanticmetadata.lire.utils;

import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Mathias Lux, mathias@juggle.at, 04.04.13, 09:30
 */
public class ImageUtilsTest extends TestCase {
    public void testCheckOpen() throws IOException {
        ArrayList<File> allImageFiles = FileUtils.getAllImageFiles(new File("D:\\DataSets\\Yahoo-GC\\test"), true);
        System.out.println("Checking " + allImageFiles.size() + " files for compatibility.");
        BufferedWriter bw = new BufferedWriter(new FileWriter("faulty.txt"));
        long ms = System.currentTimeMillis();
        int count = 0;
        for (Iterator<File> iterator = allImageFiles.iterator(); iterator.hasNext(); ) {
            File next = iterator.next();
            if (!FileUtils.isImageFileCompatible(next)) {
                String s = next.getCanonicalPath();
                bw.write(s + "\n");
                System.out.println(s);
            }
            count++;
            if (count==50) System.out.println("** - " + count + " images analyzed, " + (System.currentTimeMillis()-ms)/count + " ms / image");
            if (count==100) System.out.println("** - " + count + " images analyzed, " + (System.currentTimeMillis()-ms)/count + " ms / image");
            if (count%1000 == 0) System.out.println("** - " + count + " images analyzed, " + (System.currentTimeMillis()-ms)/count + " ms / image");
        }
        bw.close();
    }

    public void testTrim() throws IOException {
        ImageIO.write(ImageUtils.trimWhiteSpace(ImageIO.read(new File("test_trim.png"))), "png", new File("out-trim.png"));
    }
}
