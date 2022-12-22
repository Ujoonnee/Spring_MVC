package hello.typeconverter.type;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class IpPort {

    private String ip;
    private int port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpPort ipPort = (IpPort) o;
        return port == ipPort.port && ip.equals(ipPort.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }
}
