import { Button } from "@/components/ui/button";
import { CardFooter } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { useForm } from "react-hook-form";
import "./DropZone.css";

import axios from "axios";
import type { FormContent } from "../../validators/FormContent";

export default function DropZone() {
  const { register, handleSubmit } = useForm<FormContent>();
  const onSubmit = (form: FormContent) => {
    const file = form.file[0];

    const formData = new FormData();
    formData.append("file", file);

    axios
      .post(`http://localhost:8080/api/v1/1/image/upload`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.error("Erreur lors de la requête Axios :", error);
      });
  };

  return (
    <CardFooter>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Input type="file" {...register("file")} />
        <Button type="submit" className="registerButton">
          Enregistrer
        </Button>
      </form>
    </CardFooter>
  );
}
