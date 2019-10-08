package com.yinrs;

import com.yinrs.mdstream.MdStreamService;
import com.yinrs.mdstructure.MdStructure;
import com.yinrs.mdstructure.MdStructurePrinterService;
import com.yinrs.mdstructure.MdStructureWrapperService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<String> paths = new LinkedList<>();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                paths.add(args[i]);
            }
        } else {
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir);
            for (File one : file.listFiles()) {
                String fileName = one.getName();
                if (fileName.lastIndexOf(".md") == fileName.length() - 3) {
                    paths.add(one.getAbsolutePath());
                }
            }
        }

        for (String path : paths) {
            try {
                MdStreamService mdStreamService = new MdStreamService();
                String title = mdStreamService.getFileName(path);
                String content = mdStreamService.getContent(path);
                MdStructure mdStructure = new MdStructureWrapperService().wrapMdStructure(title, content);
                String structure = new MdStructurePrinterService().printMdStructure(mdStructure);
                String cases = markdown(structure);
                mdStreamService.toFile(path,cases);
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
            }
        }
        System.out.println("Done!");
    }

    private static String markdown(String structure) {
        return new StringBuffer("```math\n").append(structure).append("\n```").append("\n---").toString();
    }
}
