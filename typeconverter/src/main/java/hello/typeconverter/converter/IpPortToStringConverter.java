package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("source ={}", source);

        //IpPort 객체 -> "127.0.0.1:8080
        String result = source.getIp() + ":" + source.getPort();

        return result;
    }
}