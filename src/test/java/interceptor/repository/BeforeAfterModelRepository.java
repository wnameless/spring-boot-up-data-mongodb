package interceptor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import interceptor.model.BeforeAfterModel;

@Repository
public interface BeforeAfterModelRepository extends MongoRepository<BeforeAfterModel, String> {}
