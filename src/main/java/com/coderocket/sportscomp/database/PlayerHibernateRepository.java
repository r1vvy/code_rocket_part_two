package com.coderocket.sportscomp.database;

import com.coderocket.sportscomp.database.converter.PlayerDomainToPlayerEntityConverter;
import com.coderocket.sportscomp.database.converter.PlayerEntityToPlayerDomainConverter;
import com.coderocket.sportscomp.domain.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@AllArgsConstructor
public class PlayerHibernateRepository implements PlayerRepository {
    private final SessionFactory sessionFactory;
    private final PlayerEntityToPlayerDomainConverter entityToPlayerDomainConverter;
    private final PlayerDomainToPlayerEntityConverter domainToPlayerEntityConverter;

    @Override
    public void save(Player player) {
        var entity = domainToPlayerEntityConverter.convert(player);

        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public List<Player> findAllPlayers() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<PlayerEntity> query = builder.createQuery(PlayerEntity.class);

            Root<PlayerEntity> root = query.from(PlayerEntity.class);
            query.select(root);
            Query<PlayerEntity> q = session.createQuery(query);

            return q.getResultList()
                    .stream()
                    .map(entityToPlayerDomainConverter::convert)
                    .collect(Collectors.toList());
        } finally {
            session.close();
        }
    }
}
