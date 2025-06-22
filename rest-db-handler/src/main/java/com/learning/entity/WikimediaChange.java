package com.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wikimedia_changes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WikimediaChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String user;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "bot", nullable = false)
    private boolean bot;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "server_url")
    private String serverUrl;

    @Column(name = "full_url")
    private String fullUrl;
}
