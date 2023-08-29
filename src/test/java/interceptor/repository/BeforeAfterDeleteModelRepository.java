package interceptor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import interceptor.model.BeforeAfterDeleteModel;

@Repository
public interface BeforeAfterDeleteModelRepository
    extends MongoRepository<BeforeAfterDeleteModel, String> {}
