package com.github.simplesteph.ksm.utils

import com.security.kafka.pb.ksm._
import kafka.security.auth._
import org.apache.kafka.common.security.auth.KafkaPrincipal
import org.apache.kafka.common.utils.SecurityUtils
import org.scalatest.{ FlatSpec, Matchers }

class ProtoConversionUtilsTest extends FlatSpec with Matchers {

  "resourceToPb" should "correctly convert a resource" in {
    val resource: Resource = Resource(Topic, "foobar")
    val resourcepb: ResourcePb = ResourcePb(name = "foobar", kafkaResourceType = ResourceTypePb.RESOURCE_TYPE_TOPIC)
    ProtoConversionUtils.resourceToPb(resource) shouldBe resourcepb
  }

  "aclToPb" should "correctly convert a acl" in {
    val acl: Acl = Acl(SecurityUtils.parseKafkaPrincipal("User:john"), Allow, "*", Read)
    val aclpb: AclPb = AclPb(
      principal = Some(KafkaPrincipalPb("User", "john")),
      permissionType = PermissionTypePb.PERMISSION_TYPE_ALLOW,
      "*",
      operationType = OperationTypePb.OPERATION_TYPE_READ)
    ProtoConversionUtils.aclToPb(acl) shouldBe aclpb
  }
}
