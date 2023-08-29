package querydsl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.github.wnameless.spring.boot.up.data.mongodb.querydsl.MongoProjectionRepository;
import querydsl.model.ComplexModel;

@Repository
public interface ComplexModelRepository
    extends MongoRepository<ComplexModel, String>, MongoProjectionRepository<ComplexModel> {

  ComplexModel findByStrStartsWith(String startsWith);

}
