package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_id", columnDefinition = "INTEGER")
    private Long id;
    @OneToOne(mappedBy = "bucket")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "buckets_items",
            joinColumns = @JoinColumn(name = "bucket_id", referencedColumnName = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"))
    private List<Item> items = new ArrayList<>();

    public Bucket() {

    }

    public Bucket(Long userId) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", items=" + items + '}';
    }
}
