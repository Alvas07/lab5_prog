package system.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Ticket;
import exceptions.FileReadException;
import utils.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class XmlReader implements TicketReader {
    @Override
    public List<Ticket> readTickets(String fileName) throws FileReadException {
        if (!canRead(fileName)) {
            throw new FileReadException("Невозможно прочитать файл.");
        }

        File file = new File(fileName);
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = bufferedReaderToString(new BufferedReader(new FileReader(file)));
            List<Ticket> tickets = xmlMapper.readValue(xmlString, new TypeReference<List<Ticket>>() {});
            tickets.removeIf(t -> !Validator.isValidTicket(t));
            return tickets;
        } catch (Exception e) {
            throw new FileReadException(e.getMessage());
        }
    }

    @Override
    public boolean canRead(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не найден.");
            return false;
        }

        if (!file.isFile()) {
            System.out.println("Указанный путь не является файлом.");
            return false;
        }

        if (!file.canRead()) {
            System.out.println("Нет прав на чтение файла.");
            return false;
        }

        return true;
    }

    public String bufferedReaderToString(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
