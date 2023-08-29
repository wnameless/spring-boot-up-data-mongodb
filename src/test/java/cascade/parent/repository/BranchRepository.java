package cascade.parent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cascade.parent.model.Branch;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {}
