package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }

    @Override
    public Quote save(Quote quote) {
        if (existsById(quote.getTicker())){
            int updatedRowNo = updateOne(quote);

            if (updatedRowNo != 1){
                throw new DataRetrievalFailureException("Unable to retrieve quote");
            }
        }
        else{
            addOne(quote);
        }

        return quote;
    }

    /**
     * Helper method that saves one quote
     * @param quote
     */
    private void addOne(Quote quote){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1){
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
        }
    }

    /**
     * Helpmer method that updates one quote
     * @param quote
     * @return
     */
    private int updateOne(Quote quote){
        String update_sql = "UPDATE quote SET last_price=?, bid_price=?, " +
                "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";

        return jdbcTemplate.update(update_sql, makeUpdateValues(quote));

    }

    /**
     * Helper method that makes SQL update values objects
     * @param quote
     * @return
     */
    private Object[] makeUpdateValues(Quote quote){
        Object[] updatedValues = {quote.getTicker(), quote.getLastPrice(), quote.getBidSize(),
        quote.getBidSize(), quote.getAskPrice(), quote.getAskSize()};

        return updatedValues;
    }

    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {

        List<Quote> quotes = new ArrayList<>();

        for (Quote quote : iterable){
            Quote savedQuote = save(quote);
            quotes.add(savedQuote);
        }

        return (List<S>) quotes;

    }

    @Override
    public Optional<Quote> findById(String s) {

        String find_by_id = "SELECT * FROM " + TABLE_NAME +" WHERE " + ID_COLUMN_NAME +"=?";

        Optional<Quote> quote = Optional.empty();

        try{
            quote = (Optional<Quote>) Optional.ofNullable(jdbcTemplate.queryForObject(find_by_id, BeanPropertyRowMapper.newInstance(Quote.class), s));
        }
        catch (EmptyResultDataAccessException e){
            logger.debug("Can't find quote:" + s, e);
        }

        if (quote.isPresent())
            return quote;

        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {

        Optional<Quote> quote = null;

        try {
            quote = findById(s);
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }

        if (quote.isPresent())
            return true;

        return false;

    }

    @Override
    public Iterable<Quote> findAll() {

        String find_all = "SELECT * FROM "+TABLE_NAME;

        List<Quote> quotes = jdbcTemplate.query(find_all, BeanPropertyRowMapper.newInstance(Quote.class));

        return quotes;
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public long count() {

        String count_query = "SELECT COUNT(*) FROM " + TABLE_NAME;

        long count = jdbcTemplate.queryForObject(count_query, Long.class);

        return count;
    }

    @Override
    public void deleteById(String s) {

        String delete = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
        jdbcTemplate.update(delete, s);

    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public void deleteAll() {
        String delete = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.update(delete);
    }
}
