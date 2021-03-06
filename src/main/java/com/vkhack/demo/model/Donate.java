package com.vkhack.demo.model;

import com.vkhack.demo.model.DonateType;
import com.vkhack.demo.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Integer price;
    private Integer collectedPrice;
    private String bankCard;
    @Lob
    private byte[] image;
    private String purpose;
    @Enumerated(EnumType.STRING)
    private DonateType donateType;
    @Lob
    private String description;
    private Date startDate;
    private Date endDate;
    private String author;
    @OneToMany(mappedBy = "donate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;
}
