package registrar.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shares")
public class Share {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod", unique = true)
    private long cod;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount")
    private int amount;

    @Column(name = "total_value")
    private int totalValue;

    @Column(name = "value")
    private int value;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
