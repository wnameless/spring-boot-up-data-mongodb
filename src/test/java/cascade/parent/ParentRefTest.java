package cascade.parent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.wnameless.spring.boot.up.EnableSpringBootUp;
import cascade.parent.model.Branch;
import cascade.parent.model.Forest;
import cascade.parent.model.Leaf;
import cascade.parent.model.Tree;
import cascade.parent.repository.BranchRepository;
import cascade.parent.repository.ForestRepository;
import cascade.parent.repository.LeafRepository;
import cascade.parent.repository.TreeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
public class ParentRefTest {

  @Autowired
  ForestRepository forestRepository;
  @Autowired
  TreeRepository treeRepository;
  @Autowired
  BranchRepository branchRepository;
  @Autowired
  LeafRepository leafRepository;

  @BeforeEach
  public void cleanUp() {
    forestRepository.deleteAll();
    treeRepository.deleteAll();
    branchRepository.deleteAll();
    leafRepository.deleteAll();
  }

  @Test
  public void testEnableSpringBootUpMongoWithEnableSpringBootUp() {
    assertNotNull(AnnotationUtils.findAnnotation(AppConfig.class, EnableSpringBootUp.class));
  }

  @Test
  public void testCascadeCreate() {
    var forest = new Forest();
    var subForest = new Forest();
    var tree = new Tree();
    var branch = new Branch();
    var leaf = new Leaf();

    forest.setSubForest(subForest);
    forest.getTrees().add(tree);
    tree.getBranches().add(branch);
    // branch.getLeaves().add(leaf);

    forestRepository.save(forest);

    assertNotEquals(forest, tree.getForest());
    assertEquals(forest.getSubForest(), tree.getForest());
    assertEquals(tree, branch.getTree());
    assertEquals(null, leaf.getBranch());

    leafRepository.save(leaf);
    branch.getLeaves().add(leaf);
    branchRepository.save(branch);
    assertEquals(branch, leaf.getBranch());
  }

}
