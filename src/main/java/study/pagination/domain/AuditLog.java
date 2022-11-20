package study.pagination.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.pagination.dto.AuthDestination;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long auditLogLogId;

    @Column(name = "partner_client_id", length = 50, nullable = false)
    private String partnerClientId;

    @Column(name = "auth_destination", length = 50)
    private AuthDestination authDestination;

    @Column(name = "signature", length = 512)
    private String signature;

    @Column(name = "broker_tx_id", length = 100)
    private String brokerTxId;

    public AuditLog(String partnerClientId, String authDestination, String signature, String brokerTxId) {
        this.partnerClientId=partnerClientId;
        this.authDestination = AuthDestination.findBy(authDestination);
        this.signature=signature;
        this.brokerTxId=brokerTxId;
    }

    public String getAuthDestination() {
        return authDestination.getCode();
    }

    public void setAuthDestination(String authDestination) {
        this.authDestination=AuthDestination.findBy(authDestination);
    }

}
