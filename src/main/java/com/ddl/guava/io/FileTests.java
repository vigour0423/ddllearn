package com.ddl.guava.io;

import static com.google.common.base.Preconditions.*;
import static java.lang.System.*;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class FileTests {

    public static void main(String[] args) throws IOException {
        String testFilePath = "d:\\test.txt";
        String testFilePath2 = "d:\\test2.txt";
        File testFile = new File(testFilePath);
        CounterLine counter = new CounterLine();
        Files.readLines(testFile, Charsets.UTF_8, counter);
        System.out.println(counter.getResult());
        demoEqual(testFilePath, testFilePath2);

    }

    static class CounterLine implements LineProcessor<Integer> {
        private int rowNum = 0;

        @Override
        public boolean processLine(String line) throws IOException {
            rowNum++;
            return true;
        }

        @Override
        public Integer getResult() {
            return rowNum;
        }
    }

    /**
     * 演示向文件中写入字节流
     * @param fileName 要写入文件的文件名
     * @param contents 要写入的文件内容
     */
    public void demoFileWrite(final String fileName, final String contents) {
        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
        checkNotNull(contents, "Unable to write null contents.");
        final File newFile = new File(fileName);
        try {
            Files.write(contents.getBytes(), newFile);
        } catch (IOException fileIoEx) {
            err.println("ERROR trying to write to file '" + fileName + "' - "
                    + fileIoEx.toString());
        }
    }

    /**
     * 演示如何使用guava的Files.copy方法复制文件
     * @param sourceFileName 复制的源文件名
     * @param targetFileName 目标文件名
     */
    public void demoSimpleFileCopy(final String sourceFileName, final String targetFileName) {
        checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        try {
            Files.copy(sourceFile, targetFile);
        } catch (IOException fileIoEx) {
            err.println("ERROR trying to copy file '" + sourceFileName
                    + "' to file '" + targetFileName + "' - " + fileIoEx.toString());
        }
    }

    /**
     * 演示 Files.equal(File,File) 来比较两个文件的内容
     * @param fileName1 比较的文件1文件名
     * @param fileName2 比较的文件2文件名
     */
    public static void demoEqual(final String fileName1, final String fileName2) {
        checkNotNull(fileName1, "First file name for comparison must NOT be null.");
        checkNotNull(fileName2, "Second file name for comparison must NOT be null.");
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        try {
            out.println(
                    "File '" + fileName1 + "' "
                            + (Files.equal(file1, file2) ? "IS" : "is NOT")
                            + " the same as file '" + fileName2 + "'.");
        } catch (IOException fileIoEx) {
            err.println(
                    "ERROR trying to compare two files '"
                            + fileName1 + "' and '" + fileName2 + "' - " + fileIoEx.toString());
        }
    }
}
