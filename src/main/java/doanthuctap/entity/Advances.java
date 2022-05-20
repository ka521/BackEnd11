package doanthuctap.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "advances1")
public class Advances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    @Type(type="date")
    private Date Start;

    @Column(name = "money")
    private int Money;


    @Column(name = "employeeId")
    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Advances() {

    }

    public Advances(Date start, int money) {
        super();
        Start = start;
        Money = money;
    }

    public Date getStart() {
        return Start;
    }

    public void setStart(Date start) {
        Start = start;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }




}
