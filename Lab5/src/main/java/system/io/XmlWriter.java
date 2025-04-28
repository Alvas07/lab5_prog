package system.io;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Ticket;
import exceptions.FileWriteException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;

public class XmlWriter implements TicketWriter {
    @Override
    public void writeTicketsToFile(String fileName, Collection<Ticket> tickets) throws FileWriteException {
        if (!canWrite(fileName)) {
            throw new FileWriteException("Невозможно записать в файл.");
        }

        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tickets);
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
                writer.write(xmlString);
                writer.flush();
            }
        } catch (Exception e) {
            throw new FileWriteException(e.getMessage());
        }
    }

    @Override
    public boolean canWrite(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не найден.");
            return false;
        }

        if (!file.isFile()) {
            System.out.println("Указанный путь не является файлом.");
            return false;
        }

        if (!file.canWrite()) {
            System.out.println("Нет прав на запись в файл.");
            return false;
        }

        return true;
    }
}
