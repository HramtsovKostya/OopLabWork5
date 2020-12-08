package com.khramtsov.io;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveOpenDialog extends JFileChooser {
    public static final String JSON_FILTER = "json";
    public static final String XML_FILTER = "xml";
    public static final String BINARY_FILTER = "bin";

    private static final String CURRENT_DIRECTORY_PATH =
        "G:\\ООП на Java\\Лаб. работы\\Лаб. работа 5\\Lab5\\save";

    private final JFrame frame;
    private final FileFilter jsonFilter;
    private final FileFilter xmlFilter;
    private final FileFilter binFilter;
    private String path;

    public SaveOpenDialog(JFrame frame) {
        super(CURRENT_DIRECTORY_PATH);
        this.frame = frame;

        jsonFilter = new FileNameExtensionFilter(
            "JSON-файлы (*.json)", JSON_FILTER);
        xmlFilter = new FileNameExtensionFilter(
            "XML-файлы (*.xml)", XML_FILTER);
        binFilter = new FileNameExtensionFilter(
            "Бинарные файлы (*.bin)", BINARY_FILTER);

        addChoosableFileFilter(jsonFilter);
        addChoosableFileFilter(xmlFilter);
        addChoosableFileFilter(binFilter);

        setFileSelectionMode(FILES_ONLY);
        translateUI();
    }

    public int save() {
        setDialogTitle("Сохранение файла");
        return super.showSaveDialog(frame);
    }

    public int open() {
        setDialogTitle("Открытие файла");
        return super.showOpenDialog(frame);
    }

    public String getPath() {
        path = getSelectedFile().getAbsolutePath();
        FileFilter filter = getFileFilter();
        String extension = ".";

        if (filter == jsonFilter) extension += JSON_FILTER;
        else if (filter == xmlFilter) extension += XML_FILTER;
        else if (filter == binFilter) extension += BINARY_FILTER;

        if (!path.endsWith(extension)) path += extension;
        return path;
    }

    public String getFilter() {
        if (path.endsWith(SaveOpenDialog.JSON_FILTER)) {
           return JSON_FILTER;
        } else if (path.endsWith(SaveOpenDialog.XML_FILTER)) {
            return XML_FILTER;
        } else if (path.endsWith(SaveOpenDialog.BINARY_FILTER)) {
            return BINARY_FILTER;
        } return null;
    }

    private void translateUI() {
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.lookInLabelText", "Директория");
        UIManager.put("FileChooser.saveInLabelText", "Сохранить в директорию");
        UIManager.put("FileChooser.openInLabelText", "Открыть в директории");
        UIManager.put("FileChooser.folderNameLabelText", "Путь директории");
        updateUI();
    }
}